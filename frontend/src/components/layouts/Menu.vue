<template>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark shadow-sm">
            <div class="container-fluid">
                <a class="mx-2 navbar-brand" href="#">
                    <img alt="logo" width="35px" src="../../assets/logo.png">
                </a>
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
                        <li class="nav-item dropdown">
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
                        <div class="mx-2 botaoLogin">
                            <div v-if="!logado">
                                <router-link class="text-decoration-none" to="/login">
                                    <button class="btn btn-light me-2" @click="this.logar()">Entrar</button>
                                </router-link>
                            </div>
                            <div v-else>
                                <router-link class="text-decoration-none" to="/">
                                    <button class="btn btn-danger d-flex" @click="this.sair()">Sair</button>
                                </router-link>
                            </div>
                        </div>
                        <router-link to="/criar-conta" class="btn-link">
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
            logado: false
        }
    },
    methods: {
        logar() {
            this.logado = true;
        },
        sair() {
            this.logado = false;
        }
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