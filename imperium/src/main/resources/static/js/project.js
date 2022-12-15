// DRAG AND DROP TASK
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

// OPEN / CLOSE TASK CREATION
function openCreateTask(val) {
  document.getElementById('create-task-container' + val).style.display = "block";
}

function closeCreateTask(val) {
  document.getElementById('create-task-container' + val).style.display = "none";
}

// SIDE MODAL TO EDIT TASK
function openNav(val) {
  console.log('edit-task-modal' + val)
  document.getElementById('edit-task-modal' + val).style.width = "25%";
}

function closeNav(val) {
  document.getElementById('edit-task-modal' + val).style.width = "0";
}