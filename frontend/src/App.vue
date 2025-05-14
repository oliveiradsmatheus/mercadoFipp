<template>
    <Menu/>
    <FormCategoria/>
</template>

<script>
import Menu from "./components/layouts/Menu.vue";
import FormCategoria from "./components/FormCategoria.vue";

export default {
    name: 'App',
    components: {
        FormCategoria,
        Menu
    }
}
</script>

<style>
:root {
    --amarelo: #FFC107;
    --vermelho: #E55555;
    --azul: #236AEE;
    --preto: #282A36;
    --cinza: #71758a;
    --preto-translucido: rgba(40, 42, 54, 0.3);
    --branco: #F2F2F2;
    --branco-translucido: rgba(248, 248, 242, 0.3);
    --roxo: #603D8E;
    --rosa: #FF79C6;
}

#app {
    font-family: "JetBrains Mono", sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: var(--preto);
    margin: 0;
    padding: 0;
    background-color: var(--preto);
}
</style>
