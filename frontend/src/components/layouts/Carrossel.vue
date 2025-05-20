<template>
    <div class="col-md-6">
        <div id="anuncioCarousel" class="carousel carousel-dark slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="../../assets/fonte.jpg" class="d-block w-100 rounded" alt="Foto 1">
                </div>
                <div class="carousel-item">
                    <img src="../../assets/fonte.jpg" class="d-block w-100 rounded" alt="Foto 2">
                </div>
                <div class="carousel-item">
                    <img src="../../assets/fonte.jpg" class="d-block w-100 rounded" alt="Foto 3">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>
    </div>
</template>

<script>
export default {
    name: "Carrossel"
}
</script>

<style scoped>
</style>