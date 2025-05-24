<template>
    <div class="container text-center mt-4">
        <h1 class="text-danger mb-3">403</h1>
        <p class="fs-3"><span class="text-danger">Acesso Negado!</span></p>
        <p class="lead">
            Você não tem permissão para acessar esta página.
        </p>
        <router-link class="text-decoration-none" to="/">
            <button class="btn btn-warning mb-2" type="button">Voltar</button>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "NaoAutorizado"
}
</script>

<style scoped>
h1 {
    font-size: 12rem;
}
</style>