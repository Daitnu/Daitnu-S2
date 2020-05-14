import { RenameCategoryParam } from '~/@types/request/category';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';

export const CATEGORY_RENAME = 'CATEGORY_RENAME' as const;
export const CATEGORY_RENAME_REQUEST = 'CATEGORY_RENAME_REQUEST' as const;
export const CATEGORY_RENAME_SUCCESS = 'CATEGORY_RENAME_SUCCESS' as const;
export const CATEGORY_RENAME_FAILURE = 'CATEGORY_RENAME_FAILURE' as const;

export const categoryRenameRequest = (payload: RenameCategoryParam) => ({
  type: CATEGORY_RENAME_REQUEST,
  payload,
});

export const categoryRenameSuccess = (payload: HTTPResponse<ResponseCategory>) => ({
  type: CATEGORY_RENAME_SUCCESS,
  payload,
});

export const categoryRenameFailure = (payload: BusinessErrorResponse) => ({
  type: CATEGORY_RENAME_FAILURE,
  payload,
});
