<script setup>
import {defineAsyncComponent} from "vue";

const props = defineProps({
	name:{
		type:String, required:true
	}
})
const icon = defineAsyncComponent(()=>
	import((`../components/icons/${props.name}.svg`))
)
</script>


<template>
	<component class="icon" :is="icon"/>
</template>

<style>
.icon{
	height: 25px;
	width: 25px;
	display: inline-block;
	background-size: contain;
	background-repeat: no-repeat;
	background-position: center;
	margin-left: 40px;
}
</style>
