import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import Axios from 'axios';

export const listTodos = createAsyncThunk(
    'todos/list',
    async () => {
        const response = await Axios.get('/api/todos');
        return response.data;
    }
);

export const addTodo = createAsyncThunk(
    'todos/add',
    async (todo) => {
        const response = await Axios.post('/api/todos', {value: todo});
        return response.data;
    }
);

const todosSlice = createSlice({
    name: 'todos',
    initialState: [],
    reducers: {},
    extraReducers: {
        [listTodos.fulfilled]: (state, action) => state.concat(action.payload),
        [addTodo.fulfilled]: (state, action) => {
            state.push(action.payload);
        }
    }
});

export default todosSlice.reducer;
