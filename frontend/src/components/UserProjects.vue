<!-- eslint-disable vue/no-multiple-template-root -->
<template>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <p class="lead fw-normal mb-0" id="recent-project">
            Recent project
        </p>
        <p class="mb-0">
            <a href="#" class="text-muted">Show all</a>
        </p>
    </div>
    <div class="row g-2">
        <div class="col mb-2" v-for="p in projects" :key="p.id">
            <figure class="snip1533">
                <figcaption style="display: inline-block">
                    <h3 style="margin-bottom: 10px" >{{p.name}}</h3>
                    <h6 style="margin-bottom: 30px">{{p.category}}</h6>
                    <div v-if="owner == true">
                        <a :href="'http://localhost:8080/home/project/delete-project/' + p.name + '/'" class="draw"
                            id="delete-btn"
                            onclick="return confirm('Are you sure you want to delete the project ?');">Delete</a>
                        <a :href="'http://localhost:8080/home/project/' + user.username + '/' + p.name + '/'"
                            class="draw" id="open-btn">Open</a>
                    </div>
                </figcaption>
            </figure>
        </div>
        <div id="no-projects" v-if="projects.length == 0">
            No projects found !
        </div>
    </div>
</template>

<script>
export default {
    name: "UserProjects",
    props: {
        user: Object,
        owner: Boolean,
        projects: Array
    }
}

</script>