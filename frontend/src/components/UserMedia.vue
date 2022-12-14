<template>
    <div class="mb-5">
        <p class="lead fw-normal mb-1">My Social Media</p>
        <div class="p-4" style="background-color: #f8f9fa">
            <div class="social-media-container">
                <form :action="user.linkedin">
                    <button type="submit" target="_blank" class="btn btn-social-icon btn-linkedin btn-rounded">
                        <i class="fa fa-linkedin"></i>
                    </button>
                </form>
    
                <form :action="user.twitter">
                    <button type="submit" class="btn btn-social-icon btn-twitter btn-rounded">
                        <i class="fa fa-twitter"></i>
                    </button>
                </form>
                <form :action="user.github">
                    <button type="submit" class="btn btn-social-icon btn-github btn-rounded">
                        <i class="fa fa-github"></i>
                    </button>
                </form>
                <form :action="user.stackOverflow">
                    <button type="submit" class="btn btn-social-icon btn-stack-overflow btn-rounded">
                        <i class="fa fa-stack-overflow"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "UserMedia",
    props: {
        user: Object
    }
}
</script>