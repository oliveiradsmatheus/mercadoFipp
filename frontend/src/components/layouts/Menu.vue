<template>
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="d-flex">
                <a class="mx-2 navbar-brand" href="#">
                    <img alt="logo" width="35px" src="../../assets/logo.png">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link class="nav-link active" to="/">Home</router-link>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                Cadastros
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <router-link class="dropdown-item" to="/cadastros/categorias">Categorias</router-link>
                                </li>
                                <li>
                                    <router-link class="dropdown-item" to="/cadastros/usuarios">Usuários</router-link>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link active" to="/sobre">Sobre</router-link>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="busca col-5 d-flex">
                <form class="nav-link d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search"/>
                    <button class="btn btn-primary" type="submit">Buscar</button>
                </form>
            </div>
            <div class="mx-2 botaoLogin">
                <div v-if="!logado">
                    <router-link class="text-decoration-none" to="/login">
                        <button class="btn btn-primary d-flex" @click="this.logar()">Logar</button>
                    </router-link>
                </div>
                <div v-else>
                    <router-link class="text-decoration-none" to="/">
                    <button class="btn btn-danger d-flex" @click="this.sair()">Sair</button>
                    </router-link>
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
    display: flex;
    justify-content: space-between;
}
</style>