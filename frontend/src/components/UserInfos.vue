<!-- eslint-disable vue/no-multiple-template-root -->
<template>
    <p class="lead fw-normal mb-1">Information</p>
    <div class="mb-5 flex-container">
        <div class="p-4 profile-infos" style="background-color: #f8f9fa">
            <p><strong>Email :</strong> {{user.email}}</p>
            <p><strong>Mobile :</strong> {{user.phoneNumber}}</p>
            <p><strong>Company :</strong> {{user.company}}</p>
        </div>
        <div class="p-4 profile-infos" style="background-color: #f8f9fa">
            <p><strong>Jobtitle : </strong> {{user.jobtitle}}</p>
            <p><strong>Languages : </strong></p>
            <p><strong>blabla : </strong></p>
        </div>
    </div>
</template>

<script>
export default {
    name: "UserInfos",
    props: {
        user: Object
    }
}
</script>