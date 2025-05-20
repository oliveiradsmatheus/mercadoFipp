<template>
    <Menu/>
    <router-view/>
</template>

<script>
import Menu from "@/components/layouts/Menu.vue";

export default {
    name: 'App',
    components: {
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
    --amarelo2: #ecc459;
    --verde: #04A6A6;
    --verde-escuro: #037F8B;
    --azul2: #253F72;
    --vermelho2: #e5557b;
    --vermelho3: #922a45;
    --roxo: #3C1D3F;
    --rosa: #FF79C6;
    --creme: #F2D7C2;
}

#app {
    background-color: var(--preto);
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
</style>
