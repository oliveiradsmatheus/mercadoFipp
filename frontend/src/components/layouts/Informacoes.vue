<template>
    <div class="col-md-6 p-3 border rounded">
        <h2 class="mb-4">{{ this.anuncio.titulo }}</h2>
        <h2 class="text-success">R$ {{ parseFloat(anuncio.preco).toFixed(2) }}</h2>
        <p><strong>Descrição:</strong></p>
        <p>{{ anuncio.descricao }}.</p>
        <p><strong>Categoria:</strong> {{ anuncio.categoria.nome }}</p>
        <p><strong>Data de criação:</strong> {{ anuncio.data }}</p>
        <hr>
        <h5>Vendedor</h5>
        <p>
            <strong>Usuário:</strong> {{ anuncio.usuario.nome }}
        </p>
    </div>
</template>

<script>
export default {
    name: "Informacoes",
    props: {
        anuncio: null
    }
}
</script>

<style scoped>
</style>