<template>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark shadow-sm">
            <div class="container-fluid">
                <router-link class="navbar-brand mx-2 active" to="/">
                    <img alt="logo" width="35px" src="../../assets/logo.png">
                </router-link>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        aria-controls="navbarConteudo" aria-expanded="false" aria-label="Alternar navegação">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarConteudo">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link class="nav-link active" to="/">Home</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link active" to="/anuncios">Anúncios</router-link>
                        </li>
                        <li v-if="this.usuario && this.usuario.nivel === '0'" class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                Administrador
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <router-link class="dropdown-item" to="/adm/anuncios">Anúncios
                                    </router-link>
                                </li>
                                <li>
                                    <router-link class="dropdown-item" to="/adm/categorias">Categorias
                                    </router-link>
                                </li>
                                <li>
                                    <router-link class="dropdown-item" to="/adm/usuarios">Usuários
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <div class="d-flex">
                        <p v-if="this.usuario" class="text text-white m-2">Seja bem-vindo, {{ this.usuario.nome }}</p>
                        <div class="mx-2 botaoLogin">
                            <router-link v-if="this.usuario" class="text-decoration-none" to="/">
                                <button class="btn btn-danger d-flex" @click="this.sair()">
                                    Sair
                                </button>
                            </router-link>
                            <router-link v-else class="text-decoration-none" to="/login">
                                <button class="btn btn-light me-2">
                                    Entrar
                                </button>
                            </router-link>
                        </div>
                        <router-link v-if="!this.usuario" to="/criar-conta" class="btn-link">
                            <button class="btn btn-outline-light">Registrar</button>
                        </router-link>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</template>

<script>
export default {
    name: "Menu",
    data() {
        return {
            usuario: null,
        }
    },
    methods: {
        sair() {
            localStorage.removeItem("usuario");
            this.usuario = null;
        }
    },
    mounted() {
        this.usuario = JSON.parse(localStorage.getItem("usuario"));
    }
}
</script>

<style scoped>
.navbar {
    background-color: var(--preto);
    display: flex;
    justify-content: space-between;
}
</style>