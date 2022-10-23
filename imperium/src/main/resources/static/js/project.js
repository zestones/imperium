function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.currentTarget.appendChild(document.getElementById(data));
}

function openCreateTask() {
   document.getElementById('create-task-container').style.display = "block";
}

function closeCreateTask() {
   document.getElementById('create-task-container').style.display = "none";
}