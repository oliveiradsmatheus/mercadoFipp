<template>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Perguntas e Respostas</h4>
            </div>
            <ul v-if="perguntas.length===0" class="list-group list-group-flush">
                <li class="list-group-item bg-body-tertiary">
                    <p class="mt-2">Nenhuma pergunta foi feita.</p>
                </li>
            </ul>
            <ul v-else class="list-group list-group-flush">
                <li v-for="pergunta in perguntas" class="list-group-item bg-body-tertiary">
                    <div class="fw-bold text-primary">Pergunta:</div>
                    <div class="mb-2">{{ pergunta.texto }}</div>
                    <div class="ms-3">
                        <span class="fw-semibold text-success">Resposta do vendedor:</span><br>
                        <p>{{ pergunta.resposta }}</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: "Perguntas",
    props: {
        perguntas: {}
    }
}
</script>

<style scoped>
</style>