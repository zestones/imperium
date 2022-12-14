<template>
    <div class="mb-5">
        <p class="lead fw-normal mb-1">About me</p>
        <div class="p-4" style="background-color: #f8f9fa">
            <p>{{bio}}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "UserBio",
    props: {
        bio: String
    }
}
</script>