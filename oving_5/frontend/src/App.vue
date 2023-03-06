<template>
  <main class="mainApp">
    <CodeInputComponent />
    <CompiledComponent />
  </main>
</template>

<script>
import CodeInputComponent from "./components/CodeInputComponent.vue";
import CompiledComponent from "./components/CompiledComponent.vue";

export default {
  name: 'App',
  components: {
    CodeInputComponent,
    CompiledComponent
  }
}
</script>

<style scoped>
.mainApp{
  background-color: #2c3e50;
}
</style>
