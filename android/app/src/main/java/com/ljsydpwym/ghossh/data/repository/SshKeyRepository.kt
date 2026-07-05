package com.ljsydpwym.ghossh.data.repository

import com.ljsydpwym.ghossh.data.db.SshKeyDao
import com.ljsydpwym.ghossh.model.SshKey
import kotlinx.coroutines.flow.Flow

class SshKeyRepository(
    private val dao: SshKeyDao,
) {
    fun observeAll(): Flow<List<SshKey>> = dao.observeAll()

    suspend fun getById(id: Long): SshKey? = dao.getById(id)

    suspend fun insert(key: SshKey): Long = dao.insert(key)

    suspend fun deleteById(id: Long) = dao.deleteById(id)
}
