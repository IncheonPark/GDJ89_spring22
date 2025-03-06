

const checkAll = document.getElementById("checkAll");
const checks = document.getElementsByClassName("checks");
const cart_delete = document.querySelector("#cart-delete")


cart_delete.addEventListener('click', ()=>{
    // ./cartDelete?productNum=1&productNum=2
    let url = new URL("cartDelete", window.location);
    //url.search="productNum=1&productNum=2";

    let nums = [];

    for(let c of checks){
        if(c.checked){
           url.searchParams.append("productNum", c.value);
           nums.push(c.value);
        }
    }

    fetch(url)
    .then(result => result.text())
    .then(result=>{
        alert('삭제되었다')
        location.reload();
        // for(let n of nums){
        //     document.getElementById(n).remove();
        // }
    })
    .catch(e=>{
        alert('관리자에게 문의')
    })
})


checkAll.addEventListener('click', ()=>{
    //console.log(checkAll.getAttribute("checked")); null
    for(let check of checks){
        check.checked=checkAll.checked;
    
    }
})

for(let check of checks){
    check.addEventListener("click", ()=>{
        let r=true;
        for(let c of checks){
            if(!c.checked){
                r=false;
            }
        }

        checkAll.checked=r;
    })
}