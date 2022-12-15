// DRAG AND DROP TASK
function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
  var data = ev.dataTransfer.getData("text"); 
  document.getElementById(data).style.opacity = "5%"
}

function allowDrop(ev) {
  ev.preventDefault();
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.currentTarget.appendChild(document.getElementById(data));
  document.getElementById(data).style.opacity = "100%"
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
  document.getElementById('edit-task-modal' + val).style.width = "25%";
}

function closeNav(val) {
  document.getElementById('edit-task-modal' + val).style.width = "0";
}

// DROP DOWN MENU ASSIGNEE

/* When the user clicks on the button, toggle between hiding and showing the dropdown content */
function toogleDropDown(val) {
  document.getElementById(val).classList.toggle("show");
}

function filterFunction(val) {
  console.log(val)

  var input, filter, ul, li, a, i;
  input = document.getElementById("search-input" + val);
  filter = input.value.toUpperCase();

  div = document.getElementById("assignee-dropdown" + val);
  console.log(div)

  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}