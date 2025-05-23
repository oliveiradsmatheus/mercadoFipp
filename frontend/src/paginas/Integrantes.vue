<template>
    <div class="container my-5 p-5  text-dark rounded shadow-sm w-75">
        <h1 class="mb-4">Integrantes</h1>
        <div class="row g-3 m-5">
            <div class="col-md-4">
                <div class="p-3 border rounded">
                    <h5>GABRIEL PISSININ MENOSSI</h5>
                    <p>RA: 102316570</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="p-3 border rounded">
                    <h5>
                        MATHEUS ANTONUCCI MENDONCA
                    </h5>
                    <p>RA: 102316813</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="p-3 border rounded">
                    <h5>MATHEUS OLIVEIRA DA SILVA</h5>
                    <p>RA: 102421080</p>
                </div>
            </div>
        </div>
        <router-link class="text-decoration-none" to="/">
            <button class="btn btn-warning m-4 mb-2" type="button">Voltar</button>
        </router-link>
    </div>
</template>

<script>
export default {
    name : "Integrantes"
}

</script>

<style scoped>

</style>