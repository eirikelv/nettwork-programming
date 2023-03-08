<template>
  <main class="mainApp">
    <CompiledComponent />
  </main>
</template>

<script>
import CompiledComponent from "./components/CompiledComponent.vue";

export default {
  name: 'App',
  components: {
    CompiledComponent
  }
}
</script>

<style scoped>
.mainApp{
  background-color: #2c3e50;
}
</style>
