<template>
    <div v-if="this.fotos.length === 0" class="col-md-6 align-content-center">
        <img src="../../assets/imagens/ine.jpg" class="d-block w-100 rounded" alt="Imagem Indisponível">
    </div>
    <div v-else class="col-md-6">
        <div id="carouselExampleDark" class="carousel carousel-dark slide">
            <div class="carousel-indicators">
                <button
                    v-for="(foto, index) in fotos"
                    :key="foto.id"
                    :data-bs-target="'#carouselExampleDark'"
                    :data-bs-slide-to="index"
                    :class="{'active': index === 0}"
                    :aria-label="'Slide ' + (index + 1)"
                ></button>
            </div>
            <div class="carousel-inner">
                <div
                    v-for="(foto, index) in fotos"
                    :key="foto.id"
                    :class="{'carousel-item': true, 'active': index === 0}"
                    data-bs-interval="10000"
                >
                    <img :src="foto.img64" class="d-block w-100 rounded" alt="Imagem">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</template>

<script>export default {
    name: "Carrossel",
    props: {
        fotos: Array
    }
}
</script>

<style scoped>

</style>