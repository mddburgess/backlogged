import {configureStore} from '@reduxjs/toolkit';
import {combineReducers} from 'redux';
import library from './library';

const store = configureStore({
    reducer: combineReducers({
        library
    })
});

export default store;
