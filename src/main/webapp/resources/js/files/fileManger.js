const add_file = document.getElementById("add_file");
const files = document.getElementById("files");
const del = document.getElementsByClassName("del");
let count=0;


files.addEventListener('click', function(e){
    if(e.target.classList.contains('del')){
        e.target.parentElement.remove();
        count--;
    }
})



add_file.addEventListener("click", function(){
    if(count>4){
        alert('파일은 최대 5개만 가능');
        return;
    }

    //--- div 추가가
    let div = document.createElement("div");
    div.classList.add("input-group", "mb-3")

    //--- label 추가
    let child = document.createElement("label");
    
    let attr = document.createAttribute("class");
    attr.value="form-label";
    child.setAttributeNode(attr);

    attr = document.createAttribute("for")
    attr.value="attach"+count
    child.setAttributeNode(attr);

    div.append(child);

    //--- input 추가
    child = document.createElement("input");
    attr = document.createAttribute("type");
    attr.value="file";
    child.setAttributeNode(attr);

    attr = document.createAttribute("class");
    attr.value="form-control";
    child.setAttributeNode(attr);

    attr = document.createAttribute("name");
    attr.value="attaches";
    child.setAttributeNode(attr);

    attr = document.createAttribute("id");
    attr.value="attach"+count
    child.setAttributeNode(attr);

    div.append(child);    

    //--- button 추가
    child = document.createElement('button');
    child.innerText='X';
    
    child.type="button";
    

    child.classList.add('input-group-text', 'btn', 'btn-outline-danger', 'del')
    div.append(child);

    files.append(div);

    count++;
})

//