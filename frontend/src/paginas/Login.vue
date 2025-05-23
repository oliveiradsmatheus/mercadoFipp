<template>
    <div class="container mt-5 justify- w-50">
        <h1 class="mb-3">Entre</h1>
        <form class="p-3">
            <div class="mb-3 form-group">
                <label for="usuario">Usuário</label>
                <input type="text" class="form-control" id="usuario" placeholder="Informe o Usuário">
            </div>
            <div class="mb-3 form-group">
                <label for="senha">Senha</label>
                <input type="password" class="form-control" id="senha" placeholder="Senha">
                <small class="form-text text-muted">Nós nunca compartilhos os seus dados com ninguém.</small>
            </div>
            <button type="submit" class="btn btn-warning mt-2 mx-2">Entrar</button>
            <router-link class="text-decoration-none" to="/">
                <button class="btn btn-secondary mt-2 mx-2" type="button">Voltar</button>
            </router-link>
        </form>
        <div class="text-center mt-3">
            <p>
                Não tem uma conta?
                <router-link to="/criar-conta" class="btn-link">Crie sua conta aqui</router-link>
            </p>
        </div>
    </div>
</template>

<script>
import {toast} from "vue3-toastify";

export default {
    name: "Login",
    mounted() {
        if (localStorage.getItem('cadastroRealizado') === 'true') {
            toast.success('Seu cadastro realizado com sucesso!', {
                autoClose: 2000
            });
            localStorage.removeItem('cadastroRealizado');
        }
        document.body.style.overflow = 'hidden';
    }
}
</script>

<style scoped>
</style>