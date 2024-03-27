import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import '@fortawesome/fontawesome-svg-core'
import '@fortawesome/vue-fontawesome'
import '@fortawesome/free-solid-svg-icons'


/* import the fontawesome core */
//import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
//import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

//import {faUser} from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
//library.add(faUser)


const app = createApp(App)

//app.component('font-awesome-icon', FontAwesomeIcon)
app.use(createPinia())
app.use(router)

app.mount('#app')
