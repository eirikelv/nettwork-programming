<template>
  <div class="compileFeedback">
    <textarea id="compiledCode" name="compiledCode" placeholder="compiled code comes here" rows="20" cols="50" readonly>
      {{compiledContent || 'compiled code comes here'}}
    </textarea>
  </div>
</template>

<script>
export default {
  name: "CompiledComponent"
}
</script>

<style scoped>
  .compileFeedback{
    padding: 30px;
  }

  #compiledCode{
    color: chartreuse;
    background-color: #282828;
    cursor: default;
    text-align: left;
  }
  #compiledCode::placeholder {
    color: chartreuse;
  }
</style>