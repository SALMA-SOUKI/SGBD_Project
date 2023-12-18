/*   const editor = document.querySelector('#editor')
        const result = document.querySelector('.result')
        const run = document.querySelector('#run')
        const save = document.querySelector('#save')
        const copy = document.querySelector('#copy')

        editor.addEventListener('keyup',()=>{
            var html = editor.textContent;
            result.srcdoc = html
        })
        // handle run button
        run.addEventListener('click',()=>{
            var html = editor.textContent;
            result.srcdoc = html
        })

        // handle paste event
        editor.addEventListener('paste',(e)=>{
            e.preventDefault();
            var text = e.clipboardData.getData("text/plain");
            document.execCommand("insertText",false,text);
        })

        // handle save event
        save.addEventListener('click',()=>{
            var html = editor.innerHTML
            var link = document.createElement('a');
            link.setAttribute('download','index.html')
            link.setAttribute('href','data:text/html;charset=utf-8,'+ encodeURIComponent(html))
            link.click()
        })

        // handle copy event
        copy.addEventListener('click',()=>{
            copy_data(editor)
        })
          // refrence: https://stackoverflow.com/questions/36639681/how-to-copy-text-from-a-div-to-clipboard
          function copy_data(containerid) {
            var range = document.createRange();
            range.selectNode(containerid);
            window.getSelection().removeAllRanges();
            window.getSelection().addRange(range);
            document.execCommand("copy");
            window.getSelection().removeAllRanges();
            alert("Code copied");
        }

        // THats it our code editor is ready*/
    