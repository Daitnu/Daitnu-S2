import axios, { AxiosInstance } from 'axios';
import { BusinessErrorResponse } from '~/@types/response/error';
import { HTTPResponse } from '~/@types/response/success';
import { RequestParam } from '~/@types/request/common';

const API_SERVER = 'http://localhost:8080';
const MEDIA_TYPE = {
  JSON: 'application/json',
};

const getHttpResponse = async <T>({
  fn,
  url,
  data,
}): Promise<HTTPResponse<T> | BusinessErrorResponse> => {
  try {
    const { status, data: resData } = await fn(url, data);
    const successResponse: HTTPResponse<T> = { status, data: resData };
    return successResponse;
  } catch (err) {
    let errResponse: BusinessErrorResponse = { status: 0, message: '', code: '', errors: [] };
    if (!err.response) {
      errResponse = {
        status: 500,
        message: err.message,
        code: null,
        errors: null,
      };
      return errResponse;
    }
    const { status, message, code, errors } = err.response.data;
    errResponse = { status, message, code, errors };
    return errResponse;
  }
};

const toQueryString = (obj): string =>
  Object.entries(obj)
    .map(([k, v]) => `${k}=${v}`)
    .reduce((prev, cur) => `${prev}&${cur}`);

export default class Api {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: API_SERVER,
      headers: {
        'Content-Type': MEDIA_TYPE.JSON,
        Accept: MEDIA_TYPE.JSON,
      },
      timeout: 5000,
    });
  }

  /**
   * @method
   * @template T Response Type
   * @template D Request Type. default: undefined
   * @param {RequestParam<D>} param - { url: string, data: D | undefined }: RequestParam<D>
   * @returns {Promise<HTTPResponse<T> | BusinessErrorResponse>}
   */
  public async get<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    if (data !== undefined) {
      url += '?' + toQueryString(data);
      data = undefined;
    }
    return getHttpResponse<T>({ fn: this.api.get, url, data });
  }

  /**
   * @method
   * @template T Response Type
   * @template D Request Type. default: undefined
   * @param {RequestParam<D>} param - { url: string, data: D | undefined }: RequestParam<D>
   * @returns {Promise<HTTPResponse<T> | BusinessErrorResponse>}
   */
  public async post<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.post, url, data });
  }

  /**
   * @method
   * @template T Response Type
   * @template D Request Type. default: undefined
   * @param {RequestParam<D>} param - { url: string, data: D | undefined }: RequestParam<D>
   * @returns {Promise<HTTPResponse<T> | BusinessErrorResponse>}
   */
  public async patch<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.patch, url, data });
  }

  /**
   * @method
   * @template T Response Type
   * @template D Request Type. default: undefined
   * @param {RequestParam<D>} param - { url: string, data: D | undefined }: RequestParam<D>
   * @returns {Promise<HTTPResponse<T> | BusinessErrorResponse>}
   */
  public async delete<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.delete, url, data });
  }
}
