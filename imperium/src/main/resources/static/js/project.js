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

function openCreateTask(val) {
   document.getElementById('create-task-container' + val).style.display = "block";
}

function closeCreateTask(val) {
   document.getElementById('create-task-container' + val).style.display = "none";
}