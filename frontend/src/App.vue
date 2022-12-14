<!-- eslint-disable vue/no-multiple-template-root -->
<template>
  <NavBar :tabs="tabs" />
  <div class="row d-flex justify-content-center align-items-center h-100">
    <div class="col col-lg-9 col-xl-11">
      <div class="card">
          <ProfileHeader :id="id" :owner="owner" :idVisited="idVisited" />
        </div>
      </div>
    </div>
</template>

<script>

import NavBar from './components/NavBar.vue'
import ProfileHeader from './components/ProfileHeader.vue'


// var uri = window.location.toString();

// if (uri.indexOf("?") > 0) {
//   var clean_uri = uri.substring(0, uri.indexOf("?"));
//   window.history.replaceState({}, document.title, clean_uri);
// }

export default {
  name: 'App',
  components: {
    NavBar,
    ProfileHeader
  },
  mounted: function () {
    this.loadData()
  },
  beforeMount: function () {
    this.getURLInfos()
  },
  data: () => ({
    visited: {},
    owner: Boolean,
    id: Number,
    idVisited: Number,
  }
  ), 
  methods: {
    loadData: async function () {
      let res = await fetch('/api/users/' + this.idVisited) // hard coded :(, not HATEOAS
      let body = await res.json()
      this.visited = body
    },
    getURLInfos: function () {
      const queryString = window.location.search;
      const urlParams = new URLSearchParams(queryString);

      const idVisited = urlParams.get('visited')
      const idCurrent = urlParams.get('current')

      this.owner = (Number(idCurrent) === Number(idVisited))
      this.id = Number(idCurrent)
      this.idVisited = Number(idVisited)

      if (idVisited == null || idCurrent == null)
        location.replace("http://localhost:8080/")
    }
  },
}
</script>

<style>

</style>
