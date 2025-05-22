<template>
    <div class="card m-3 p-2 shadow" style="width: 23rem;">
        <img v-if="anuncio.fotos && anuncio.fotos.length > 0"
             :src="anuncio.fotos[0].img64"
             class="card-img-top rounded"
             :alt="anuncio.titulo">
        <img v-else src="../../assets/imagens/ine.jpg" class="card-img-top rounded" alt="Imagem indisponível"/>
        <div class="card-body mt-2">
            <h5 class="card-title text-truncate">{{ anuncio.titulo }}</h5>
            <p class="card-text text-truncate">{{ anuncio.descricao }}</p>
        </div>
        <router-link class="text-decoration-none" :to="`/anuncio/${anuncio.id}`">
            <button class="btn btn-primary mb-3">
                Mais detalhes
            </button>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "Card",
    props: {
        anuncio: Object
    }
}
</script>

<style scoped>
.card {
    height: 520px;
}

.card-img-top {
    height: 300px;
}
</style>