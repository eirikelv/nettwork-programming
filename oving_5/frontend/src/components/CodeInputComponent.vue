<template>
  <div class="codeInput">
    <div class="inputSection">
      <textarea id="code" name="code" placeholder="write code here:" rows="20" cols="50"></textarea>
    </div>
    <button class="compileButton" role="button">Compile</button>
  </div>
</template>

<script>
  export default {
    name: "CodeInputComponent"
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

  #code{
    color: chartreuse;
    background-color: #282828;
  }
  #code::placeholder {
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
</style>