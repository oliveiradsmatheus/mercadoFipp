<template>
    <footer class="text-white fixed-bottom shadow-sm">
        <div class="container-fluid py-3">
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-center">
                <div class="mb-2 mb-md-0">
                    <strong>Mercado FIPP</strong> &copy; 2025 — Todos os direitos reservados
                </div>
                <div>
                    <a href="#" class="text-white text-decoration-none me-3">Privacidade</a>
                    <router-link class="text-white text-decoration-none me-3" to="/integrantes">Integrantes</router-link>
                    <router-link class="text-white text-decoration-none me-3" to="/sobre">Sobre</router-link>
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: "Rodape"
}
</script>

<style scoped>
footer {
    background-color: var(--preto);
}
</style>