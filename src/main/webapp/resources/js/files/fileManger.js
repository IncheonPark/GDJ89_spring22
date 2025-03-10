
//import fileDelete from "/resources/js/files/fileDelete.js";
const add_file = document.getElementById("add_file");
const files = document.getElementById("files");
const del = document.getElementsByClassName("del");

let count=files.getAttribute("data-files-size");

const file_delete = document.querySelectorAll(".file-delete");

for (let f of file_delete){
    f.addEventListener('click', ()=>{
    let check = confirm("정말 삭제?? 복구 불가");
    if(check){
        //DB에서 삭제, HDD 삭제
        let num = f.getAttribute("data-file-num");
        let kind = f.getAttribute("data-kind");

        //동기식 비동기식
        //'post'
        let url=`/${kind}/fileDelete`;
        url ='./fileDelete';
        fetch(url, {
            method:'POST',
            headers:{
                "Content-type":"application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: `fileNum=${num}` //'fileNum='+num

        })
        .then(r => r.text())
        .then(r => {
            if(r.trim()*1>0){
                //1.파일갯수 count 수 수정
                count--;
                //2. Element 삭제
                f.parentElement.remove()
               
            }else {
                alert('파일 삭제 실패');    
            }
        })
        .catch(e => {
            alert('파일 삭제 오류');
        })
        

        
    }
    })
}




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