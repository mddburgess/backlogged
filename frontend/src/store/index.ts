import {configureStore} from "@reduxjs/toolkit";
import API from "api";

const store = configureStore({
    reducer: {
        [API.reducerPath]: API.reducer
    },
    middleware: (getDefaultMiddleware => getDefaultMiddleware().concat(API.middleware))
})

export default store;
