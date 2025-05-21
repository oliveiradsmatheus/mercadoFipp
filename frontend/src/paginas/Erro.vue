<template>
    <div class="container text-center mt-4">
            <img src="../assets/imagens/erro.png" alt="erro" class="w-50">
            <p class="fs-3"> <span class="text-danger">Ops!</span> Página não encontrada.</p>
        <p class="lead">
            A página que você está procurando não existe ou foi movida.
        </p>
        <router-link class="text-decoration-none" to="/">
            <button class="btn btn-primary mb-2" type="button">Voltar</button>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "Erro"
}
</script>

<style scoped>
h1 {
    font-size: 12rem;
}
</style>