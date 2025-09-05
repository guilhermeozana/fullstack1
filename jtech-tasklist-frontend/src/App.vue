<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="wrapper">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container d-flex justify-content-center">
        <RouterLink class="navbar-brand" to="/">Task List App</RouterLink>
      </div>
    </nav>
  </div>
  <div class="content">
    <RouterView/>
  </div>
</template>

<style scoped>
  .navbar {
    padding-top: 0.6rem;
    padding-bottom: 0.6rem;
  }

  .navbar-brand {
    font-size: 1.8rem;
    font-weight: bold;
  }

  .content {
    max-width: 800px;
    margin: 80px auto 30px auto;
  }

</style>
