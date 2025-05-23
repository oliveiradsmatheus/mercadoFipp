<template>
    <div class="card h-100 d-flex flex-column" style="height: 420px;">
        <img
            :src="anuncio.fotos[0]?.img64 || 'https://via.placeholder.com/300x200?text=Sem+Imagem'"
            class="card-img-top imagem-fixa"
            alt="Imagem do anúncio"
        />
        <div class="card-body d-flex flex-column">
            <h5 class="card-title text-truncate">{{ anuncio.titulo }}</h5>
            <p class="card-text text-muted small">
                {{ anuncio.descricao.substring(0, 100) }}...
            </p>
            <div class="botao mt-auto">
                <p class="text-success fw-bold">R$ {{ anuncio.preco.toFixed(2) }}</p>
                <router-link :to="`/anuncio/${anuncio.id}`">
                    <button class="btn btn-outline-warning w-100">Ver mais</button>
                </router-link>
            </div>
        </div>
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
.imagem-fixa {
    width: 100%;
    height: 200px;
    object-fit: cover;
    object-position: center;
    border-top-left-radius: 0.25rem;
    border-top-right-radius: 0.25rem;
}

button {
    color: var(--preto);
}

.card {
    background-color: var(--branco);
}
</style>