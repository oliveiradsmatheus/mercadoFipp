<template>
    <Menu v-if="this.$route.path !== '/login' && this.$route.path !== '/criar-conta'"/>
    <div class="pagina">
        <router-view/>
        <ToastifyContainer/>
    </div>
    <Rodape/>
</template>

<script>
import Menu from "@/components/layouts/Menu.vue";
import {ToastifyContainer} from "vue3-toastify";
import Rodape from "@/components/layouts/Rodape.vue";

export default {
    name: 'App',
    components: {
        Rodape,
        ToastifyContainer,
        Menu
    }
}
</script>

<style>
:root {
    --preto: #282A36;
    --fundo-app: #20212a;
    --branco: #F2F2F2;
}

#app {
    background-color: var(--fundo-app);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: var(--preto);
    min-height: 100vh;
}

.container {
    background-color: var(--branco);
    border-radius: 6px;
    margin-top: 20px;
    margin-bottom: 20px;
    padding: 30px 60px;
}

.card-header {
    background-color: var(--preto);
}

.pagina {
    margin-bottom: 80px;
    background: var(--fundo-app);
}
</style>
