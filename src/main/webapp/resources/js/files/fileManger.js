const add_file = document.getElementById("add_file");
const files = document.getElementById("files");

add_file.addEventListener("click", function(){
    let div = document.createElement("div");

    let child = document.createElement("label");
    let attr = document.createAttribute("class");
    attr.value="form-label";
    child.setAttributeNode(attr);

    div.append(child);

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

    div.append(child);    

    files.append(div);
})