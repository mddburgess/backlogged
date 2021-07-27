import {BackendError} from "types/BackendError";
import {createSlice, isRejectedWithValue, Middleware} from "@reduxjs/toolkit";

type ErrorState = {
    data?: BackendError,
    show: boolean
}

export const errorSlice = createSlice({
    name: "error",
    initialState: {
        data: undefined,
        show: false
    } as ErrorState,
    reducers: {
        set: (state, action) => {
            state.data = action.payload;
            state.show = true;
        },
        clear: (state) => {
            state.show = false;
        }
    }
})

export const errorActions = errorSlice.actions;

export const errorMiddleware: Middleware = (store) => (next) => (action) => {
    if (isRejectedWithValue(action)) {
        store.dispatch(errorSlice.actions.set(action.payload.data));
    }
    return next(action);
}
