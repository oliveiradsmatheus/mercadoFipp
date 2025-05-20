<template>
    <div class="container mt-5">
        <h1 class="mb-3">Entre</h1>
        <form class="p-3">
            <div class="mb-3 form-group">
                <label for="usuario">Usuário</label>
                <input type="email" class="form-control" id="usuario" placeholder="Informe o Usuário">
            </div>
            <div class="mb-3 form-group">
                <label for="senha">Senha</label>
                <input type="password" class="form-control" id="senha" placeholder="Senha">
                <small class="form-text text-muted">Nós nunca compartilhos os seus dados com ninguém.</small>
            </div>
                <button type="submit" class="btn btn-primary mt-2 mx-3">Entrar</button>
                <router-link class="text-decoration-none" to="/">
                    <button class="btn btn-primary mt-2 mx-3" type="button">Voltar</button>
                </router-link>
        </form>
    </div>
</template>

<script>
export default {
    name: "Login"
}
</script>

<style scoped>
.container {
    width: 50%;
}
</style>