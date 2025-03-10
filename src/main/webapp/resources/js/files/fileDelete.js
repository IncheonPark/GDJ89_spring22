
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

        
       }
    })
}
