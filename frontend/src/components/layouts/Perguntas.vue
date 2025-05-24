<template>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header text-white">
                <h4 class="mb-0">Perguntas e Respostas</h4>
            </div>
            <ul v-if="perguntas.length===0" class="list-group list-group-flush">
                <li class="list-group-item bg-body-tertiary">
                    <p class="mt-2">Nenhuma pergunta foi feita.</p>
                </li>
            </ul>
            <ul v-else class="list-group list-group-flush">
                <li v-for="pergunta in perguntas" class="list-group-item bg-body-tertiary">
                    <div class="fw-bold text-dark">Pergunta:</div>
                    <div class="mb-2">{{ pergunta.texto }}</div>
                    <div v-if="pergunta.resposta" class="ms-3">
                        <span class="fw-semibold text-success">Resposta do vendedor:</span><br>
                        <p>{{ pergunta.resposta }}</p>
                    </div>
                    <div v-else>
                        <div v-if="this.usuario && vendedor === this.usuario.id">
                            <router-link class="text-decoration-none"
                                         :to="`/responder/${idAnuncio}/${pergunta.id}`">
                                <button class="btn btn-secondary m-2" type="button">Responder</button>
                            </router-link>
                        </div>
                        <div>
                            <span class="text-secondary text-truncate">O vendedor ainda não respondeu a pergunta.</span><br>
                        </div>
                    </div>
                </li>
            </ul>
            <ul v-if="this.usuario && this.usuario.id !== vendedor" class="list-group list-group-flush">
                <li class="list-group-item bg-body-tertiary">
                    <router-link class="text-decoration-none" :to="`/perguntar/${idAnuncio}`">
                        <button class="btn btn-warning mb-2" type="button">Faça uma pergunta!</button>
                    </router-link>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: "Perguntas",
    props: {
        perguntas: Array,
        idAnuncio: Number,
        vendedor: Number
    },
    data() {
        return {
            usuario: null
        }
    },
    mounted() {
        this.usuario = JSON.parse(localStorage.getItem("usuario"));
    }
}
</script>

<style scoped>

</style>