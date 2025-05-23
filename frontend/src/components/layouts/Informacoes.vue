<template>
    <div class="col-md-6 p-3 border rounded">
        <h2 class="mb-3">{{ this.anuncio.titulo }}</h2>
        <h2 class="text-success mb-3">R$ {{ parseFloat(anuncio.preco).toFixed(2) }}</h2>
        <p><strong>Descrição:</strong></p>
        <p class="mb-1">{{ anuncio.descricao }}.</p>
        <p class="mb-1"><strong>Categoria:</strong> {{ anuncio.categoria.nome }}</p>
        <p><strong>Data de criação:</strong> {{ anuncio.data }}</p>
        <hr>
        <div class="col-auto d-flex flex-column align-items-center">
            <button class="btn btn-warning w-50 mb-2">Comprar</button>
            <button class="btn btn-outline-dark w-50">Adicionar ao carrinho</button>
        </div>
        <p class="mt-3 mb-2">Vendido por
            <router-link :to="`/anuncios/usuario/${anuncio.usuario.id}`">
                {{ anuncio.usuario.nome }}
            </router-link>
        </p>
    </div>
</template>

<script>
export default {
    name: "Informacoes",
    props: {
        anuncio: Object
    }
}
</script>

<style scoped>
</style>