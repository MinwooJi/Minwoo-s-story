
<template>
  <div>
    <img
      :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage"
      onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
      class="profile"
    />
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>