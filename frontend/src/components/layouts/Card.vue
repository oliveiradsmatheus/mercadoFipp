<template>
    <div class="card m-3 p-2 shadow" style="width: 23rem;">
        <img src="../../assets/fonte.jpg" class="card-img-top" alt="{{anuncio.titulo}}">
        <div class="card-body">
            <h5 class="card-title">{{ anuncio.titulo }}</h5>
            <p class="card-text">{{ anuncio.descricao }}</p>
            <router-link class="text-decoration-none" :to="`/anuncio/${anuncio.id}`">
                <a href="#" class="btn btn-primary">
                    Mais detalhes
                </a>
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: "Card",
    props: {
        anuncio: null
    }
}
</script>

<style scoped>

</style>