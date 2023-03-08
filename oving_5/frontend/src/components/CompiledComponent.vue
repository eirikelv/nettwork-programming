<template>
  <div class="codeInput">
    <div class="inputSection">
      <textarea id="codeTextArea" name="codeArea" v-model="code"
                placeholder="write code here:" rows="20" cols="50"></textarea>
    </div>
    <button class="compileButton" @click="compile" :disabled="loading" role="button">Compile</button>
  </div>
  <div class="compiledComponent">
    <div class="compileFeedback">
      <!--<div v-if="!showLoader">comiled code comes here:</div>-->
      <!--<div v-if="showLoader" class="loader"> </div>-->
      <div v-if="error" class="error">{{ error }}</div>
      <div v-if="compiledCode" class="compildeCode">{{ compiledCode }}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CompiledComponent",
  data() {
    return {
      code: "",
      compiledCode: "",
      error: null,
      loading: false,
    };
  },
  methods: {
    compile() {
      if(!this.code.trim()){
          console.log(this.code);
          console.log("ble en feil linje 36");
          return; 
      }
      console.log("det finnes input")
      console.log(this.code.trim())
      this.error = null;
      this.loading = true;
      axios.post("http://localhost:1234/compiler", {
        code: this.code,
      })

          .then(response =>{
            console.log(response);
            this.compiledCode = response.data.compiledCode;
          })
          .catch(error => {
            console.error(error)
            this.error = error;
          })
          .finally( ()=> {
            console.log("finally")
            this.loading = false;
      });
    }
  }
}
</script>

<style scoped>

.codeInput{

  display: grid;
  grid-template-rows: 4fr 1fr;
  padding: 30px;
}
.inputSection{
  padding-bottom: 30px;
}

#codeTextArea{
  color: chartreuse;
  background-color: #282828;
}
#codeTextArea::placeholder {
  color: chartreuse;
}

.compileButton {
  max-width: 450px;
  padding: 0.6em 2em;
  border: none;
  outline: none;
  color: rgb(255, 255, 255);
  font-size: x-large;
  background: #111;
  cursor: pointer;
  position: relative;
  z-index: 0;
  border-radius: 10px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.compileButton:before {
  content: "";
  background: linear-gradient(
      45deg,
      #ff0000,
      #ff7300,
      #fffb00,
      #48ff00,
      #00ffd5,
      #002bff,
      #7a00ff,
      #ff00c8,
      #ff0000
  );
  position: absolute;
  top: -2px;
  left: -2px;
  background-size: 400%;
  z-index: -1;
  filter: blur(5px);
  -webkit-filter: blur(5px);
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  animation: glowing-compileButton 20s linear infinite;
  transition: opacity 0.3s ease-in-out;
  border-radius: 10px;
}

@keyframes glowing-compileButton {
  0% {
    background-position: 0 0;
  }
  50% {
    background-position: 400% 0;
  }
  100% {
    background-position: 0 0;
  }
}

.compileButton:after {
  z-index: -1;
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: #222;
  left: 0;
  top: 0;
  border-radius: 10px;
}

  .compiledComponent{
    padding: 30px;
  }

  .compileFeedback{
    padding: 30px;
    color: chartreuse;
    background-color: #282828;
    cursor: default;
    text-align: left;
    min-height: 300px;
  }
  .loader{

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