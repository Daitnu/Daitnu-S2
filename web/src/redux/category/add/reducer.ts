import { CATEGORY_ADD } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseCategory } from '~/@types/response/category';

const categoryAddReducer = makeApiReducer<ResponseCategory>(CATEGORY_ADD);

export default categoryAddReducer;
