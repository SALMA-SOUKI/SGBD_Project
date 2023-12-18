package dao;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Selection {
	private String login;
	private String db;
	
	public HashSet<ArrayList<String>> get(String q,String login, String db){
	
		this.login = login;
    	this.db = db;
    	
	//public static void main(String[] args) {
		// get nom>etudiants
		// get nom>etudiants [nom==nouhaila]
		// get nom>etudiants [nom==nouhaila && age==20]
		// dot product
		// get nom,age>etudiants
		// get *>etudiants
		// get nom,prix>etudiant,produit
		
		//get nom,age>etudiants [nom==nouhaila || nom==moncef && age==21]
		
		//get nom,age>etudiants [(nom==nouhaila&&age==20) && nom==moncesf || (nom==nouhaila&&age==20) ]
		//get nom,age>etudiants [(nom==nouhaila&&age==20) || (nom==moncef&&age==20)]
		int pointer = 0;
		Query query = null ;
		query = new Query(q,login,db);
		query.Columns();
		query.TableNames();
		query.Conditions();
		
		
		if(query.getColumns().get(0).equals("*")) {
    		
    		ArrayList<String> rows = new ArrayList<String>();
    		
    		
            File file2 = new File("C:\\SGBD\\"+login+"\\"+db+"\\"+ query.getTableName().get(0) +"\\metaData.csv"); //"+query.getTableName().get(0)+"
            try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
                String line2;

                while ((line2 = reader2.readLine()) != null) {
                		rows.add(line2.split(";")[0]);
                }
                
    		
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
        

    		query.setColumns(rows);
    	}
		
    
        
		HashSet<ArrayList<String>> records = new HashSet<ArrayList<String>>();
		ArrayList<String> res = new ArrayList<String>();

		
		// Create a File object representing the file you want to read
        File file = new File("C:\\SGBD\\"+login+"\\"+db+"\\"+ query.getTableName().get(0) +"\\Data.csv"); //"+query.getTableName().get(0)+"
        pointer =1;
        // Use a BufferedReader to read the data from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read each line of the file
            String line = reader.readLine();

            while (line != null) {
                // Process the data on the line
            	
            	
            	String[] words = line.split(",");
            
            	
            	for (String column : query.getColumns()) {
            		
            		if(!query.getConditions().isEmpty() && query.getConditions().size() == 1 ) {
            			if(query.getConditions().get(0).contains("==")) {

            				
            				if(words[ query.indexOf(query.getConditions().get(0).split("==")[0])].equals(query.getConditions().get(0).split("==")[1])) {
            					ArrayList<String> record = new ArrayList<String>();
                        		res.add(words[ query.indexOf(column) ]);
                        		//records.add(res);



            			    }
            		     }
            			
            			if(query.getConditions().get(0).contains("!=")) {
            
            				if(!words[ query.indexOf(query.getConditions().get(0).split("==")[0])].equals(query.getConditions().get(0).split("!=")[1])) {
            					ArrayList<String> record = new ArrayList<String>();
                        		res.add(words[ query.indexOf(column) ]);
                        		//records.add(res);
            				}
            			}
            			
            		}else if(!query.getConditions().isEmpty() && query.getConditions().size() != 1 ) {
            			
            			int counterAnd = 0;
            			int counterOr = 0;
            			int operatorOr = 0;
            			int operatorAnd = 0;
            			
            			for(int j=1; j<query.getConditions().size()-1 ; j+=2) {
            				
            				if(query.getConditions().get(j).equals("&&")) {
            					counterAnd+=1;
            				}
            				if(query.getConditions().get(j).equals("||")) {
            					counterOr+=1;
            				}
        				}
            			
            			
            			
            			
            			
            		
    					operatorAnd =0;
    					operatorOr = 0;
    					int opAnd =0;
    					int opOr = 0;

            			
            			for(int i=0 ; i<query.getConditions().size()-1; i=i+2) {

            				if(query.getConditions().get(i+1).equals("&&")) {
            					
            					

                				if(query.getConditions().get(i).contains("&&")) {
                					

                					
                					String[] subCondition = query.getConditions().get(i).split("&&");
                					subCondition[0] = subCondition[0].replace("(", "");
                					subCondition[0] = subCondition[0].replace(")", "");
                					subCondition[1] = subCondition[1].replace("(", "");
                					subCondition[1] = subCondition[1].replace(")", "");


                					if(subCondition[0].contains("==")) {
                						if(query.test(words[query.indexOf(subCondition[0].split("==")[0])],"==",subCondition[0].split("==")[1]) && query.test(words[query.indexOf(subCondition[1].split("==")[0])],"==",subCondition[1].split("==")[1])){
                    						

                    						operatorAnd +=1;
                    						
                    					}
                						
                        		     }
                        			
                					
                					
                				}
                				

                				if(query.test(words[query.indexOf(query.getConditions().get(i+2).split("==")[0])],"==",query.getConditions().get(i+2).split("==")[1])){
            						

            						operatorAnd +=1;
            						
            					}
                				

                				if(query.test(words[query.indexOf(query.getConditions().get(i).split("==")[0])],"==",query.getConditions().get(i).split("==")[1])){
            						

            						operatorAnd +=1;
            						
            					}
                				
                					if(query.getConditions().get(i+2).contains("&&")) {
                					
                					String[] subCondition = query.getConditions().get(i+2).split("&&");
                					subCondition[0] = subCondition[0].replace("(", "");
                					subCondition[0] = subCondition[0].replace(")", "");
                					subCondition[1] = subCondition[1].replace("(", "");
                					subCondition[1] = subCondition[1].replace(")", "");


                					if(subCondition[0].contains("==")) {
                						
                						if(query.test(words[query.indexOf(subCondition[0].split("==")[0])],"==",subCondition[0].split("==")[1]) && query.test(words[query.indexOf(subCondition[1].split("==")[0])],"==",subCondition[1].split("==")[1])){


                    						operatorAnd +=1;
                    						
                    					}
                						
                        		     }
                        			
                					
                					
                				}
                					
                					
                					
                					if(operatorAnd == 2) {
                						
                						opAnd +=1;
                					}
                				
                					
                				
                				
            					
            				}
            				
            				
            				
            				
            			if(query.getConditions().get(i+1).equals("||")) {
            					
            					

                				if(query.getConditions().get(i).contains("&&")) {
                					

                					
                					String[] subCondition = query.getConditions().get(i).split("&&");
                					subCondition[0] = subCondition[0].replace("(", "");
                					subCondition[0] = subCondition[0].replace(")", "");
                					subCondition[1] = subCondition[1].replace("(", "");
                					subCondition[1] = subCondition[1].replace(")", "");


                					if(subCondition[0].contains("==")) {
                						if(query.test(words[query.indexOf(subCondition[0].split("==")[0])],"==",subCondition[0].split("==")[1]) && query.test(words[query.indexOf(subCondition[1].split("==")[0])],"==",subCondition[1].split("==")[1])){
                    						

                    						operatorOr +=1;
                    						
                    					}
                						
                        		     }
                        			
                					
                					
                				}
                				
                				
                				if(query.test(words[query.indexOf(query.getConditions().get(i+2).split("==")[0])],"==",query.getConditions().get(i+2).split("==")[1])){
            						

            						operatorOr +=1;
            						
            					}
                				

                				if(query.test(words[query.indexOf(query.getConditions().get(i).split("==")[0])],"==",query.getConditions().get(i).split("==")[1])){
            						

            						operatorOr +=1;
            						
            					}
                				
                				
                					if(query.getConditions().get(i+2).contains("&&")) {
                					
                					String[] subCondition = query.getConditions().get(i+2).split("&&");
                					subCondition[0] = subCondition[0].replace("(", "");
                					subCondition[0] = subCondition[0].replace(")", "");
                					subCondition[1] = subCondition[1].replace("(", "");
                					subCondition[1] = subCondition[1].replace(")", "");


                					if(subCondition[0].contains("==")) {
                						
                						if(query.test(words[query.indexOf(subCondition[0].split("==")[0])],"==",subCondition[0].split("==")[1]) && query.test(words[query.indexOf(subCondition[1].split("==")[0])],"==",subCondition[1].split("==")[1])){


                    						operatorOr +=1;
                    						
                    					}
                						
                        		     }
                        			
                					
                					
                				}
                					
                					
                					
                			
                					if(operatorOr >= 1) {
                						opOr = 1;
                						
                					}
                				
            					
            				}
            			
            			
            			
            				
            			}
            			

            			if((counterAnd!=0 && counterAnd == opAnd) || opOr == 1) {
            				//System.out.println("done");
            				//System.out.println(line);
            				res.add(words[ query.indexOf(column) ]);

            				
            			}
            			
            			for(int i=0 ; i<query.getConditions().size()-1; i=i+2) {
            				
            				
            				
            				if(query.getConditions().get(i+1).equals("&&")) {
            					operatorAnd =0;
            					if(query.test(words[query.indexOf(query.getConditions().get(i).split("==")[0])],"==",query.getConditions().get(i).split("==")[1]) && query.test(words[query.indexOf(query.getConditions().get(i+2).split("==")[0])],"==",query.getConditions().get(i+2).split("==")[1])){
            						

            						operatorAnd +=1;
            						
            					}
            				}
            				
            				
            					
            				
            				if(query.getConditions().get(i+1).equals("||")) {
                    			operatorOr = 0;
                    			

            					if( (operatorAnd != 0 && counterAnd == operatorAnd) || query.test(words[query.indexOf(query.getConditions().get(i).split("==")[0])],"==",query.getConditions().get(i).split("==")[1]) || query.test(words[query.indexOf(query.getConditions().get(i+2).split("==")[0])],"==",query.getConditions().get(i+2).split("==")[1])){
            						
            						operatorOr +=1;
            						
            						

            					}
            				}
            			}
            			
            			


            			if((operatorAnd != 0 && counterAnd == operatorAnd) || operatorOr==1){ //counterOr == operatorOr
            			ArrayList<String> record = new ArrayList<String>();
                	    record.add(words[ query.indexOf(column) ]);
                		//records.add(record);
        				//res.add(words[ query.indexOf(column) ]);

                		
                		
                		
            			}
            			
            			
            		}else {
            		ArrayList<String> record = new ArrayList<String>();
            		
            		record.add(words[ query.indexOf(column) ]);
            		//records.add(record);
    				res.add(words[ query.indexOf(column) ]);

            		}
            		
            		
            	}

            	
            	
            	boolean dup = false;

            	if(!res.isEmpty()) {
            	for(ArrayList<String> ob : records) {
            			if(ob.equals(res)) {
            				dup=true;
            				System.out.println("zy");
            			}
            		
            	}
            	
            	if(!dup) {
            		records.add(res);
            	}
            	
            	}
            	
                line = reader.readLine();
        		pointer += 1;

        		res = new ArrayList<String>();

            }
        } catch (IOException e) {
            // Handle any errors that occurred while reading the file
            e.printStackTrace();
        }
        
        
        
        
        return records;
    	
	}

	
	public static void main(String[] args) {
		
		 String query = "get *>user";
	     Selection deleteQuery = new Selection();
	     System.out.println(deleteQuery.get(query, "nouhaila", "Ecommerce"));
	}

	
}
