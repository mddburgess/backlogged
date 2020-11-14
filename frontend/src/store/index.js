import {configureStore} from '@reduxjs/toolkit';
import {combineReducers} from 'redux';
import todos from './todos';

const store = configureStore({
    reducer: combineReducers({
        todos
    })
});

export default store;
