<template>
    <Menu/>
    <FormCategoria/>
    <FormUsuario/>
</template>

<script>
import Menu from "@/components/layouts/Menu.vue";
import FormCategoria from "@/components/FormCategoria.vue";
import FormUsuario from "@/components/FormUsuario.vue";

export default {
    name: 'App',
    components: {
        FormUsuario,
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
    --cinza: #D2D4E2;
    --branco: #F2F2F2;
    --roxo: #603D8E;
    --rosa: #FF79C6;
}

#app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: var(--preto);
}

.container {
    background-color: var(--branco);
    border-radius: 6px;
    margin-top: 20px;
    margin-bottom: 20px;
    padding: 30px 60px;
}
</style>
